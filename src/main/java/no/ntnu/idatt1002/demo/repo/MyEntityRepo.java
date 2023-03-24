package no.ntnu.idatt1002.demo.repo;

import java.util.Arrays;
import java.util.List;
import no.ntnu.idatt1002.demo.data.MyEntity;

/**
 * Repository for the MyEntity-entity.
 *
 * @author nilstes
 */
public class MyEntityRepo {

  /**
   * Get object with given id.
   *
   * @param  id  the entity id
   * @return     an instance of MyEntity
   */
  public MyEntity getMyEntity(String id) {
    // Get connection (maybe use pool?)
    // Do some SQL
    // Return som real data

    return new MyEntity("id", "name");
  }

  /**
   * Adding checkstyle here to avoid detection.

   * @param someParameter input string parameter.
   * @return an array as a list.
   */
  public List<MyEntity> findMyEntities(String someParameter) {
    // Get connection (maybe use pool?)
    // Do some SQL
    // Return som real data

    return Arrays.asList(new MyEntity("id1", "name1"), new MyEntity("id2", "name2"));
  }

  public void addMyEntity(MyEntity obj) {
    // Get connection (maybe use pool?)
    // Do some SQL
  }

  public void deleteMyEntity(String id) {
  // Get connection (maybe use pool?
  // Do some SQL
  }
}
