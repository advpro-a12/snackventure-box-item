package id.ac.ui.cs.advprog.snackventure.boxitem.repository;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.Box;
import id.ac.ui.cs.advprog.snackventure.boxitem.model.BoxItem;
import id.ac.ui.cs.advprog.snackventure.boxitem.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BoxItemRepository extends JpaRepository<BoxItem, UUID>{
    @Query("select b from BoxItem b where b.box.id = ?1")
    List<Item> findAllItemsInBox(UUID box_id);
}
