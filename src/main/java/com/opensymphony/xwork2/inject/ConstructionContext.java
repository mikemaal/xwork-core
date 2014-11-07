package com.opensymphony.xwork2.inject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * Context of a dependency construction. Used to manage circular references.
 *
 * @author crazybob@google.com (Bob Lee)
 */
class ConstructionContext<T> implements Serializable {

   T currentReference;

   boolean constructing;

   List<DelegatingInvocationHandler<T>> invocationHandlers;

   T getCurrentReference() {
      return currentReference;
   }

   void removeCurrentReference() {
      this.currentReference = null;
   }

   void setCurrentReference(T currentReference) {
      this.currentReference = currentReference;
   }

   boolean isConstructing() {
      return constructing;
   }

   void startConstruction() {
      this.constructing = true;
   }

   void finishConstruction() {
      this.constructing = false;
      invocationHandlers = null;
   }

   Object createProxy(Class<? super T> expectedType) {
      // TODO: if I create a proxy which implements all the interfaces of
      // the implementation type, I'll be able to get away with one proxy
      // instance (as opposed to one per caller).
      if (!expectedType.isInterface()) {
         throw new DependencyException(expectedType.getName() + " is not an interface.");
      }
      if (invocationHandlers == null) {
         invocationHandlers = new ArrayList<DelegatingInvocationHandler<T>>();
      }
      DelegatingInvocationHandler<T> invocationHandler = new DelegatingInvocationHandler<T>();
      invocationHandlers.add(invocationHandler);
      return Proxy.newProxyInstance(expectedType.getClassLoader(), new Class[] { expectedType }, invocationHandler);
   }

   void setProxyDelegates(T delegate) {
      if (invocationHandlers != null) {
         for (DelegatingInvocationHandler<T> invocationHandler : invocationHandlers) {
            invocationHandler.setDelegate(delegate);
         }
      }
   }

   static class DelegatingInvocationHandler<T> implements InvocationHandler {

      T delegate;

      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         if (delegate == null) {
            throw new IllegalStateException("Not finished constructing. Please don't call methods on this"
                  + " object until the caller's construction is complete.");
         }
         try {
            return method.invoke(delegate, args);
         } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
         } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
         } catch (InvocationTargetException e) {
            throw e.getTargetException();
         }
      }

      void setDelegate(T delegate) {
         this.delegate = delegate;
      }
   }
}
