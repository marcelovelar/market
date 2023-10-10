package com.platzi.market.persistance;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistance.crud.ProductoCrudRepository;
import com.platzi.market.persistance.entity.Producto;
import com.platzi.market.persistance.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository : Con esta anotación le indicamos que esta clase se encarga de interactuar con la base de datos


@Repository
public class ProductoRepository implements ProductRepository {

    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper mapper;

    public List<Product>getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }


    //Metodo map del optional, mas amigable y optimo para el procesador
    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    public Optional<List<Producto>> getCheaperThan(int precio,int idCategoria){
        return productoCrudRepository.findByPrecioVentaLessThanAndOrderByIdCategoria(precio,idCategoria);
    }

    @Override
    public void delete(int productoId){
        productoCrudRepository.deleteById(productoId);
    }

    @Override
    public Optional<List<Product>> getFalses(boolean b){
        Optional<List<Producto>> productos = productoCrudRepository.findByEstado(false);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<List<Product>> getCheaperThan(double price, int categoryId) {
        Optional<List<Producto>> productos = productoCrudRepository.findByPrecioVentaLessThanAndOrderByIdCategoria((int)price,categoryId);
        return productos.map(prods -> mapper.toProducts(prods));
    }

}