/*
 * Copyright (c) 2011-2013 The original author or authors
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 *     The Eclipse Public License is available at
 *     http://www.eclipse.org/legal/epl-v10.html
 *
 *     The Apache License v2.0 is available at
 *     http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.core.shareddata;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Future;


/**
 *
 * An asynchronous map.
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 *
 */
@VertxGen
public interface AsyncMap<K, V> {

  /**
   * Get a value from the map, asynchronously.
   *
   * @param k  the key
   * @param resultHandler - this will be called some time later with the async result.
   */
  void get(K k, Handler<AsyncResult<V>> resultHandler);

  /**
   * Like {@link #get(Object, Handler)}but returns a {@code Future} that will be
   * completed with the result once the operation completes.
   */
  Future<V> get(K k);

  /**
   * Put a value in the map, asynchronously.
   *
   * @param k  the key
   * @param v  the value
   * @param completionHandler - this will be called some time later to signify the value has been put
   */
  void put(K k, V v, Handler<AsyncResult<Void>> completionHandler);

  /**
   * Like {@link #put(Object, Object, Handler)}but returns a {@code Future} that will be
   * completed once the operation completes.
   */
  Future<Void> put(K k, V v);

  /**
   * Like {@link #put} but specifying a time to live for the entry. Entry will expire and get evicted after the
   * ttl.
   *
   * @param k  the key
   * @param v  the value
   * @param ttl  The time to live (in ms) for the entry
   * @param completionHandler  the handler
   */
  void put(K k, V v, long ttl, Handler<AsyncResult<Void>> completionHandler);

  /**
   * Like {@link #put(Object, Object, long, Handler)}but returns a {@code Future} that will be
   * completed once the operation completes.
   */
  Future<Void> put(K k, V v, long ttl);

  /**
   * Put the entry only if there is no entry with the key already present. If key already present then the existing
   * value will be returned to the handler, otherwise null.
   *
   * @param k  the key
   * @param v  the value
   * @param completionHandler  the handler
   */
  void putIfAbsent(K k, V v, Handler<AsyncResult<V>> completionHandler);

  /**
   * Like {@link #putIfAbsent(Object, Object, Handler)}but returns a {@code Future} that will be
   * completed once the operation completes.
   */
  Future<V> putIfAbsent(K k, V v);

  /**
   * Link {@link #putIfAbsent} but specifying a time to live for the entry. Entry will expire and get evicted
   * after the ttl.
   *
   * @param k  the key
   * @param v  the value
   * @param ttl  The time to live (in ms) for the entry
   * @param completionHandler  the handler
   */
  void putIfAbsent(K k, V v, long ttl, Handler<AsyncResult<V>> completionHandler);

  /**
   * Like {@link #putIfAbsent(Object, Object, long, Handler)}but returns a {@code Future} that will be
   * completed once the operation completes.
   */
  Future<V> putIfAbsent(K k, V v, long ttl);

  /**
   * Remove a value from the map, asynchronously.
   *
   * @param k  the key
   * @param resultHandler - this will be called some time later to signify the value has been removed
   */
  void remove(K k, Handler<AsyncResult<V>> resultHandler);

  /**
   * Like {@link #remove(Object, Handler)}but returns a {@code Future} that will be
   * completed once the operation completes.
   */
  Future<V> remove(K k);

  /**
   * Remove a value from the map, only if entry already exists with same value.
   *
   * @param k  the key
   * @param v  the value
   * @param resultHandler - this will be called some time later to signify the value has been removed
   */
  void removeIfPresent(K k, V v, Handler<AsyncResult<Boolean>> resultHandler);

  /**
   * Like {@link #removeIfPresent(Object, Object, Handler)}but returns a {@code Future} that will be
   * completed once the operation completes.
   */
  Future<Boolean> removeIfPresent(K k, V v);

  /**
   * Replace the entry only if it is currently mapped to some value
   *
   * @param k  the key
   * @param v  the new value
   * @param resultHandler  the result handler will be passed the previous value
   */
  void replace(K k, V v, Handler<AsyncResult<V>> resultHandler);

  /**
   * Like {@link #replace(Object, Object, Handler)}but returns a {@code Future} that will be
   * completed once the operation completes.
   */
  Future<V> replace(K k, V v);

  /**
   * Replace the entry only if it is currently mapped to a specific value
   *
   * @param k  the key
   * @param oldValue  the existing value
   * @param newValue  the new value
   * @param resultHandler the result handler
   */
  void replaceIfPresent(K k, V oldValue, V newValue, Handler<AsyncResult<Boolean>> resultHandler);

  /**
   * Like {@link #replaceIfPresent(Object, Object, Object, Handler)}but returns a {@code Future} that will be
   * completed once the operation completes.
   */
  Future<Boolean> replaceIfPresent(K k, V oldValue, V newValue);

  /**
   * Clear all entries in the map
   *
   * @param resultHandler  called on completion
   */
  void clear(Handler<AsyncResult<Void>> resultHandler);

  /**
   * Like {@link #clear(Handler)}but returns a {@code Future} that will be
   * completed once the operation completes.
   */
  Future<Void> clear();

  /**
   * Provide the number of entries in the map
   *
   * @param resultHandler  handler which will receive the number of entries
   */
  void size(Handler<AsyncResult<Integer>> resultHandler);

  /**
   * Like {@link #size(Handler)}but returns a {@code Future} that will be
   * completed once the operation completes.
   */
  Future<Integer> size();

}
