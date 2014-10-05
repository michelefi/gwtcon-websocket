package com.google.developers.gdgfirenze.server.serialization;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.SerializationPolicy;

public class CustomSerializationPolicy extends SerializationPolicy {

  @Override
  public boolean shouldDeserializeFields(Class<?> clazz) {
    if (clazz != null) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean shouldSerializeFields(Class<?> clazz) {
    if (clazz != null) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void validateDeserialize(Class<?> clazz) throws SerializationException {
  }

  @Override
  public void validateSerialize(Class<?> clazz) throws SerializationException {
  }

}
