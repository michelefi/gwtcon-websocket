package com.google.developers.gdgfirenze.server.serialization;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.SerializationPolicy;

public class SimpleSerializationPolicy extends SerializationPolicy {

	@Override
	public boolean shouldDeserializeFields(Class<?> clazz) {
		return isSerializable(clazz);
	}

	@Override
	public boolean shouldSerializeFields(Class<?> clazz) {
		return isSerializable(clazz);
	}

	@Override
	public void validateDeserialize(Class<?> clazz)
			throws SerializationException {
		if (!isSerializable(clazz)) {
			throw new SerializationException();
		}
	}

	@Override
	public void validateSerialize(Class<?> clazz) throws SerializationException {
		if (!isSerializable(clazz)) {
			throw new SerializationException();
		}
	}

	private boolean isSerializable(Class<?> clazz) {
		if (clazz != null) {
			if (clazz.isPrimitive()
					|| Serializable.class.isAssignableFrom(clazz)
					|| IsSerializable.class.isAssignableFrom(clazz)) {
				return true;
			}
		}
		return false;

	}
}
