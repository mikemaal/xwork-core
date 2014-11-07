package com.opensymphony.xwork2.inject;

import java.io.Serializable;

/**
 * Thrown when a dependency is misconfigured.
 *
 * @author crazybob@google.com (Bob Lee)
 */
public class DependencyException extends RuntimeException implements Serializable {

   public DependencyException(String message) {
      super(message);
   }

   public DependencyException(String message, Throwable cause) {
      super(message, cause);
   }

   public DependencyException(Throwable cause) {
      super(cause);
   }
}
