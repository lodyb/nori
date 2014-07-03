/*
 * This file is part of nori.
 * Copyright (c) 2014 Tomasz Jan Góralczyk <tomg@fastmail.uk>
 * License: ISC
 */

package com.cuddlesoft.nori.test;

import com.cuddlesoft.nori.api.clients.Gelbooru;
import com.cuddlesoft.nori.api.clients.SearchClient;

/**
 * Tests for the Gelbooru API client.
 */
public class GelbooruTest extends SearchClientTestCase {

  @Override
  protected SearchClient createSearchClient() {
    return new Gelbooru("http://gelbooru.com");
  }
}
