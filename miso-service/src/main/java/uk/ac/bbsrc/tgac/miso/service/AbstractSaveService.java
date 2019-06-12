package uk.ac.bbsrc.tgac.miso.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uk.ac.bbsrc.tgac.miso.core.data.Identifiable;
import uk.ac.bbsrc.tgac.miso.core.service.SaveService;
import uk.ac.bbsrc.tgac.miso.core.store.SaveDao;
import uk.ac.bbsrc.tgac.miso.service.exception.ValidationError;
import uk.ac.bbsrc.tgac.miso.service.exception.ValidationException;
import uk.ac.bbsrc.tgac.miso.service.security.AuthorizationException;

public abstract class AbstractSaveService<T extends Identifiable> implements SaveService<T> {

  public abstract SaveDao<T> getDao();

  @Override
  public T get(long id) throws IOException {
    return getDao().get(id);
  }

  @Override
  public long create(T object) throws IOException {
    authorizeSave(object);
    loadChildEntities(object);
    validateChange(object, null);
    return getDao().create(object);
  }

  @Override
  public long update(T object) throws IOException {
    T managed = get(object.getId());
    authorizeSave(managed);
    loadChildEntities(object);
    validateChange(object, managed);
    applyChanges(managed, object);
    return getDao().update(managed);
  }

  /**
   * Check authorization to save object. Should throw {@link AuthorizationException} if the user is not authorized to save the object.
   * Default implementation does nothing, which means the save is always authorized
   * 
   * @param object object being saved
   */
  protected void authorizeSave(T object) throws IOException {
    // do nothing
  }

  /**
   * Load any of the object's member entities from other services. Default implementation does nothing
   * 
   * @param object object being saved
   * @throws IOException
   */
  protected void loadChildEntities(T object) throws IOException {
    // do nothing
  }

  protected void validateChange(T object, T beforeChange) throws IOException {
    List<ValidationError> errors = new ArrayList<>();
    collectValidationErrors(object, beforeChange, errors);
    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }

  protected abstract void collectValidationErrors(T object, T beforeChange, List<ValidationError> errors) throws IOException;

  protected abstract void applyChanges(T to, T from);

}