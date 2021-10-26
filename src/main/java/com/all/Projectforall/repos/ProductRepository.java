package com.all.Projectforall.repos;

import com.all.Projectforall.entitys.Product;
import com.all.Projectforall.entitys.compositkey.ProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, ProductKey> {
    // select * from product where the_admin ='ahmed'  group by name, category order by category;// note that with group by you must mention the primary key field
    @Query("from Product p where p.id.theAdmin=:admin group by p.id.name,category order by category")
    public List<List<Product>>productsInGroup(@Param("admin") String admin);
    public Optional<Product> findById_NameAndId_TheAdmin(String name,String admin);
    public Optional<Product>findByCodAndId_TheAdmin(Long cod,String admin);
    public List<Product>findByCategoryAndId_TheAdmin(String categoryName,String admin);
    public List<Product>findAllById_TheAdmin(String admin);

    @Query("select p.id.name from Product p where p.id.theAdmin=:theAdmin")
    public List<String>selectNames(@Param("theAdmin") String admin);

}
