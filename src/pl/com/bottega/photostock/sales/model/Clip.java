package pl.com.bottega.photostock.sales.model;

public class Clip extends AbstractProduct {

    private Long lenght;

    public Clip(Long number,Long lenght, Money price, Boolean active ) {
        super(number, price, active);
        this.lenght = lenght;
    }

}