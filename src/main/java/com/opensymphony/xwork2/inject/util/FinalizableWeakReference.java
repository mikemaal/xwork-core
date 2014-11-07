package com.opensymphony.xwork2.inject.util;

import java.lang.ref.WeakReference;
import java.io.Serializable;

/**
 * Weak reference with a {@link com.opensymphony.xwork2.inject.util.FinalizableReference#finalizeReferent() finalizeReferent()} method which a background
 * thread invokes after the garbage collector reclaims the referent. This is a
 * simpler alternative to using a {@link java.lang.ref.ReferenceQueue}.
 *
 * @author crazybob@google.com (Bob Lee)
 */
public abstract class FinalizableWeakReference<T> extends WeakReference<T> implements FinalizableReference,
      Serializable {

   protected FinalizableWeakReference(T referent) {
      super(referent, FinalizableReferenceQueue.getInstance());
   }
}
