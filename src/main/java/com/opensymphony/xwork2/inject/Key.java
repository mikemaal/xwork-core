package com.opensymphony.xwork2.inject;

import java.io.Serializable;

/**
 * Dependency mapping key. Uniquely identified by the required type and name.
 *
 * @author crazybob@google.com (Bob Lee)
 */
class Key<T> implements Serializable {

   final Class<T> type;

   final String name;

   final int hashCode;

   private Key(Class<T> type, String name) {
      if (type == null) {
         throw new NullPointerException("Type is null.");
      }
      if (name == null) {
         throw new NullPointerException("Name is null.");
      }
      this.type = type;
      this.name = name;
      hashCode = type.hashCode() * 31 + name.hashCode();
   }

   Class<T> getType() {
      return type;
   }

   String getName() {
      return name;
   }

   @Override
   public int hashCode() {
      return hashCode;
   }

   @Override
   public boolean equals(Object o) {
      if (!(o instanceof Key)) {
         return false;
      }
      if (o == this) {
         return true;
      }
      Key other = (Key) o;
      return name.equals(other.name) && type.equals(other.type);
   }

   @Override
   public String toString() {
      return "[type=" + type.getName() + ", name='" + name + "']";
   }

   static <T> Key<T> newInstance(Class<T> type, String name) {
      return new Key<T>(type, name);
   }
}
