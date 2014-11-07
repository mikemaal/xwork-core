package com.opensymphony.xwork2.inject.util;

import java.lang.ref.PhantomReference;
import java.io.Serializable;

/**
 * Phantom reference with a {@link com.opensymphony.xwork2.inject.util.FinalizableReference#finalizeReferent() finalizeReferent()} method which a
 * background thread invokes after the garbage collector reclaims the
 * referent. This is a simpler alternative to using a {@link
 * java.lang.ref.ReferenceQueue}.
 *
 * @author crazybob@google.com (Bob Lee)
 */
public abstract class FinalizablePhantomReference<T> extends PhantomReference<T> implements FinalizableReference,
      Serializable {

   protected FinalizablePhantomReference(T referent) {
      super(referent, FinalizableReferenceQueue.getInstance());
   }
}
