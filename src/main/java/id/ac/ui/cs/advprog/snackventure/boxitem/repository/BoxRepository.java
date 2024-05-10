package id.ac.ui.cs.advprog.snackventure.boxitem.repository;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface BoxRepository extends JpaRepository<Box, UUID>{
}
