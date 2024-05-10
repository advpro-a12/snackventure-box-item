package id.ac.ui.cs.advprog.snackventure.boxitem.repository;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID>{
}
