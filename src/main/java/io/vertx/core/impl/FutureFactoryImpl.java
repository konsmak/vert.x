/*
 * Copyright (c) 2011-2014 The original author or authors
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

import io.vertx.core.Future;
import io.vertx.core.spi.FutureFactory;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@SuppressWarnings("unchecked")
public class FutureFactoryImpl implements FutureFactory {

  private static final Future EMPTY_FUTURE = new SucceededResult(null);

  @Override
  public <T> Future<T> future() {
    return new FutureImpl<>();
  }

  @Override
  public <T> Future<T> succeededFuture() {
    return EMPTY_FUTURE;
  }

  @Override
  public <T> Future<T> succeededFuture(T result) {
    return new SucceededResult<>(result);
  }

  @Override
  public <T> Future<T> failedFuture(Throwable t) {
    return new FailedResult<>(t);
  }

  @Override
  public <T> Future<T> failureFuture(String failureMessage) {
    return new FailedResult<>(failureMessage);
  }
}
