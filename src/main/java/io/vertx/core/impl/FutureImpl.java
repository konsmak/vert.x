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

package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

class FutureImpl<T> implements Future<T>, Handler<AsyncResult<T>> {

  private Handler<AsyncResult<T>> handler;
  private AsyncResult<T> asyncResult;

  /**
   * Create a future that hasn't completed yet
   */
  FutureImpl() {
  }

  @Override
  public synchronized boolean isComplete() {
    return asyncResult != null;
  }

  @Override
  public Future<T> setHandler(Handler<AsyncResult<T>> handler) {
    AsyncResult<T> asyncResult;
    synchronized (this) {
      this.handler = handler;
      asyncResult = this.asyncResult;
    }
    if (handler != null && asyncResult != null && (asyncResult.succeeded() || asyncResult.failed())) {
      handler.handle(asyncResult);
    }
    return this;
  }

  @Override
  public void complete(T result) {
    handle(AsyncResult.success(result));
  }

  @Override
  public void complete() {
    complete(null);
  }

  @Override
  public synchronized void fail(Throwable throwable) {
    handle(AsyncResult.failure(throwable));
  }

  @Override
  public void fail(String failureMessage) {
    handle(AsyncResult.failure(new NoStackTraceThrowable(failureMessage)));
  }

  @Override
  public synchronized T result() {
    return asyncResult != null ? asyncResult.result() : null;
  }

  @Override
  public synchronized Throwable cause() {
    return asyncResult != null ? asyncResult.cause() : null;
  }

  @Override
  public synchronized boolean succeeded() {
    return asyncResult != null && asyncResult.succeeded();
  }

  @Override
  public synchronized boolean failed() {
    return asyncResult != null && asyncResult.failed();
  }

  @Override
  public void handle(AsyncResult<T> event) {
    if (!event.succeeded() && !event.failed()) {
      throw new IllegalArgumentException();
    }
    Handler<AsyncResult<T>> handler;
    synchronized (this) {
      if (asyncResult != null) {
        throw new IllegalStateException("Result is already complete: " + (asyncResult.succeeded() ? "succeeded" : "failed"));
      }
      asyncResult = event;
      handler = this.handler;
    }
    if (handler != null) {
      this.handler.handle(event);
    }
  }

  @Override
  public Handler<AsyncResult<T>> completer() {
    return this;
  }
}
