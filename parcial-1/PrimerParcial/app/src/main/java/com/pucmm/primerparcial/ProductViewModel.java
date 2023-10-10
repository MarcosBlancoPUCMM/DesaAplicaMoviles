package com.pucmm.primerparcial;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private MutableLiveData<List<Product>> mutableLiveData = new MutableLiveData<>();
    private List<Product> products = new ArrayList<>();

    public ProductViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Product>> getProducts() {
        return  mutableLiveData;
    }

    public void insert(Product product) {
        products.add(product);
        mutableLiveData.setValue(products);
    }

    public void updateProducts(List<Product> updatedProducts) {
        products.clear();
        products.addAll(updatedProducts);
        mutableLiveData.setValue(products);
    }
}
