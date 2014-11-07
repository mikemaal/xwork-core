package com.opensymphony.xwork2.inject.util;

import java.lang.ref.SoftReference;
import java.io.Serializable;

/**
 * Soft reference with a {@link com.opensymphony.xwork2.inject.util.FinalizableReference#finalizeReferent() finalizeReferent()} method which a background
 * thread invokes after the garbage collector reclaims the referent. This is a
 * simpler alternative to using a {@link java.lang.ref.ReferenceQueue}.
 *
 * @author crazybob@google.com (Bob Lee)
 */
public abstract class FinalizableSoftReference<T> extends SoftReference<T> implements FinalizableReference,
      Serializable {

   protected FinalizableSoftReference(T referent) {
      super(referent, FinalizableReferenceQueue.getInstance());
   }
}
