package model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private final String name;
    private final List<Asset> assets;


    public Client(String name) {
        this.name = name;
        assets = new ArrayList<>();
    }
    public String getName(){
        return name;
    }

    public List<Asset> getAssets () {
        return assets;
    }

    public void addAsset(Product product, int quantity) {
        assets.add(new Asset(product, quantity));
    }


}
