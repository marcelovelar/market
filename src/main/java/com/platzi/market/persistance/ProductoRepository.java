package com.platzi.market.persistance;

import com.platzi.market.persistance.crud.ProductoCrudRepository;
import com.platzi.market.persistance.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository : Con esta anotación le indicamos que esta clase se encarga de interactuar con la base de datos
@Repository
public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    public List<Producto>getAll(){
        return (List<Producto>) productoCrudRepository.findAll();}

    public List<Producto> getByCategoria (int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }
    public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }
    public Optional<List<Producto>> getFalses(boolean b){
        return productoCrudRepository.findByEstado(false);
    }
    public Optional<List<Producto>> getCheaperThan(int precio,int categoria){
        return productoCrudRepository.findByPrecioVentaLessThanAndByIdCategoria(precio,categoria);
    }

    public Optional<Producto> getProducto(int id){
        return productoCrudRepository.findById(id);
    }

    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }


}
