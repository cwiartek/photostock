package pl.com.bottega.photostock.sales.infrastructure.Repositories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Picture;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.Repositories.ClientRepository;
import pl.com.bottega.photostock.sales.model.Repositories.ProductRepository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class CSVProductRepository implements ProductRepository {

    private String path;
    private ClientRepository clientRepository;

    public CSVProductRepository(String path, ClientRepository clientRepository) {
        this.path = path;
        this.clientRepository = clientRepository;
    }

    @Override
    public Product get(Long number) {

        return getOptional(number).orElseThrow(() -> new IllegalArgumentException("No such product in repo"));
        }

    @Override
    public Optional<Product> getOptional(Long number) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                Product product = getProduct(line);
                if ( product.getNumber().equals(number))
                    return Optional.of(getProduct(line));

            }
            return Optional.empty();
        } catch (FileNotFoundException e) {
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getProduct(String line) {
            String[] lineSplit = line.split(",");
            Long nr = Long.parseLong(lineSplit[0]);
            String[] tags = lineSplit[1].split(";");
            Money price = Money.valueOf(Integer.parseInt(lineSplit[2]));
            Boolean active = Boolean.valueOf(lineSplit[3]);
            String reservedByNumber = lineSplit[4];
            String ownerNumber = lineSplit[5];
            return new Picture(nr,tags,price,findClient(reservedByNumber),findClient(ownerNumber),active);

        }

    private Client findClient(String number) {
        if(number.equals("null"))
            return null;
        else
                return clientRepository.get(number);
        }

    @Override
    public void save(Product product) {

        Map<Long, Product> productMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                productMap.put(getProduct(line).getNumber(),getProduct(line));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        productMap.put(product.getNumber(),product);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Product p:productMap.values()) {
                StringBuilder line = new StringBuilder();
                for (String entry : toLine(p))
                    line.append(entry).append(",");
                line.deleteCharAt(line.lastIndexOf(","));
                bw.write(line.toString());
                bw.newLine();
            }
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private String[] toLine(Product p) {
        Picture picture = (Picture) p;
        String[] productLine = new String[6];
        productLine[0] = String.valueOf(picture.getNumber());
        if (picture.getTags().size() >0) {
            StringBuilder tags = new StringBuilder();
            picture.getTags().forEach(tag -> tags.append(tag).append(";"));
            productLine[1] = tags.deleteCharAt(tags.lastIndexOf(";")).toString();
        } else {
            productLine[1] ="";
        }
        productLine[2] = String.valueOf(p.getPrice());
        productLine[3] = String.valueOf(p.isAvailable());
        productLine[4] = String.valueOf((p.getReservedBy()) != null ? p.getReservedBy().getNumber() : null);
        productLine[5] = String.valueOf(p.getOwner().getNumber());
        return productLine;
    }

    @Override
    public List<Product> find(Client client, Set<String> tags, Money from, Money to) throws IOException {
        List<Product> products = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                Picture picture = (Picture) getProduct(line);
                if (matchesCriteria(picture, client, tags, from, to))
                    products.add(picture);
            }
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return products;

    }


}
