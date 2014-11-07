package com.opensymphony.xwork2.inject;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

/**
 * Internal context. Used to coordinate injections and support circular
 * dependencies.
 *
 * @author crazybob@google.com (Bob Lee)
 */
class InternalContext implements Serializable {

   final ContainerImpl container;

   final Map<Object, ConstructionContext<?>> constructionContexts = new HashMap<Object, ConstructionContext<?>>();

   Scope.Strategy scopeStrategy;

   ExternalContext<?> externalContext;

   InternalContext(ContainerImpl container) {
      this.container = container;
   }

   public Container getContainer() {
      return container;
   }

   ContainerImpl getContainerImpl() {
      return container;
   }

   Scope.Strategy getScopeStrategy() {
      if (scopeStrategy == null) {
         scopeStrategy = (Scope.Strategy) container.localScopeStrategy.get();
         if (scopeStrategy == null) {
            throw new IllegalStateException("Scope strategy not set. " + "Please call Container.setScopeStrategy().");
         }
      }
      return scopeStrategy;
   }

   @SuppressWarnings("unchecked")
   <T> ConstructionContext<T> getConstructionContext(Object key) {
      ConstructionContext<T> constructionContext = (ConstructionContext<T>) constructionContexts.get(key);
      if (constructionContext == null) {
         constructionContext = new ConstructionContext<T>();
         constructionContexts.put(key, constructionContext);
      }
      return constructionContext;
   }

   @SuppressWarnings("unchecked")
   <T> ExternalContext<T> getExternalContext() {
      return (ExternalContext<T>) externalContext;
   }

   void setExternalContext(ExternalContext<?> externalContext) {
      this.externalContext = externalContext;
   }
}
