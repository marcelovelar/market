package com.platzi.market.persistance.crud;

import com.platzi.market.persistance.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {
    //Esta forma sería con el query nativo
    //@Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)

    //De esta otra es con los Query Methods, más conveniente
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
    Optional<List<Producto>> findByEstado(boolean b);
    Optional<List<Producto>> findByPrecioVentaLessThanAndByIdCategoria(int precioVenta,int idCategoria);

 }
