package pl.com.bottega.photostock.sales.Misc;

import java.io.*;



public class IoPlay {

    static class Person implements Serializable {
        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    public static void main(String[] args) {
      //  basicRead();
      //  basicReadTryWithResources();
       // characterRead();
      // bufferedRead();
       // basicWrite();
       // writer();
       // printWriter();
       // writeObjects();
       // readObjects();

    }

   /* private static void readObjects() {
        try {
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\output.txt"))) {
                Object o;
                while ((o = ois.readObject()) != null) {
                    Person p = (Person) o;
                    System.out.println("%s %d",p.name, p.age);
                    System.out.println();
                }
            } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

   }
   */

    private static void writeObjects() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\output.txt"))) {
            oos.writeObject(new Person(17, "Jan Nowak"));
            oos.writeObject(new Person(17, "Janina Nowak"));
            oos.writeObject(new Person(17, "Jan Nowak"));
            oos.writeObject(new Person(17, "Jan Nowak"));

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();

        }
    }

    private static void printWriter() {
        try (OutputStream outputStream = new FileOutputStream("D:\\output.txt");
             PrintStream ps = new PrintStream(outputStream)) {

            ps.println("Zażołć gssnśź \r\n");
            ps.println("ALA ma kota \n");

        } catch (FileNotFoundException e) {
            System.out.println("Nie udalo sie otworzyc pliku");

        } catch (IOException e) {
            System.out.println("Blad wyjscia");
        }
    }

    private static void writer() {
        try (OutputStream outputStream = new FileOutputStream("D:\\output.txt");
             Writer writer = new OutputStreamWriter(outputStream,"CP1250")) {

            writer.write("Zażołć gssnśź \r\n");
            writer.write("ALA ma kota \n");

        } catch (FileNotFoundException e) {
            System.out.println("Nie udalo sie otworzyc pliku");

        } catch (IOException e) {
            System.out.println("Blad wyjscia");
        }

    }

    private static void basicWrite() {
        try (OutputStream outputStream = new FileOutputStream("D:\\output.txt",true)) {
            outputStream.write("Zażołć gssnśź".getBytes());
        } catch (FileNotFoundException e) {
            System.out.println("Nie udalo sie otworzyc pliku");

        } catch (IOException e) {
            System.out.println("Blad wyjscia");
        }
    }


    private static void basicReadTryWithResources() {
        try (InputStream inputStream = new FileInputStream("D:\\test.txt")) {
            int b;
            while ((b = inputStream.read()) != -1) {
                System.out.print((char) b);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku");
        } catch (IOException e) {
            System.out.println("Blad wyjscia");

        }
    }

    private static void bufferedRead() {
        try (InputStream inputStream = new FileInputStream("D:\\test.txt")) {
            InputStreamReader reader = new InputStreamReader(inputStream,"CP1250");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.print(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku");
        } catch (IOException e) {
            System.out.println("Blad wejscia");

        }
    }

    private static void characterRead() {
        try (InputStream inputStream = new FileInputStream("D:\\test.txt")) {
            InputStreamReader reader = new InputStreamReader(inputStream,"CP1250");
            System.out.println(reader.getEncoding());
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku");
        } catch (IOException e) {
            System.out.println("Blad wyjscia");

        }
    }





    private static void basicRead() {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream("D:\test.txt");
             int b;
              while ((b = inputStream.read()) != -1) {
                    System.out.print((char) b);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Nie ma takiego pliku");
            } catch (IOException e) {
                System.out.println("Blad wyjscia");
            }
            finally {
            if (inputStream !=null)
                try{
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Blad zamykania pliku");
                }
            }


        }

}
