/*
 * This file is part of nori.
 * Copyright (c) 2014 Tomasz Jan Góralczyk <tomg@fastmail.uk>
 * License: ISC
 */

package com.cuddlesoft.nori.test;

import android.test.AndroidTestCase;

import com.cuddlesoft.nori.api.Image;
import com.cuddlesoft.nori.api.SearchResult;
import com.cuddlesoft.nori.api.clients.Danbooru;
import com.cuddlesoft.nori.api.clients.SearchClient;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Tests for the Danbooru 2.x API.
 */
public class DanbooruTests extends AndroidTestCase {
  // TODO: Test API key authentication.

  public void testSearchUsingTags() throws Throwable {
    // TODO: Ideally this should be mocked, so testing doesn't rely on external APIs.
    // Create a new client connected to the Danbooru API.
    final SearchClient client = new Danbooru("http://danbooru.donmai.us");
    // Retrieve a search result.
    final SearchResult result = client.search("duck");

    // Make sure we got results back.
    assertThat(result.getImages()).isNotEmpty();
    // Verify metadata for each returned image.
    for (Image image : result.getImages()) {
      ImageTests.verifyImage(image);
    }

    // Check rests of the values.
    assertThat(result.getResultCount()).isPositive();
    assertThat(result.getCurrentOffset()).isEqualTo(0L);
    assertThat(result.getQuery()).hasSize(1);
    assertThat(result.getQuery()[0].getName()).isEqualTo("duck");
    assertThat(result.hasNextPage()).isTrue();
  }

  public void testSearchUsingTagsAndOffset() throws Throwable {
    // TODO: Ideally this should be mocked, so testing doesn't rely on external APIs.
    // Create a new client connected to the Danbooru API.
    final SearchClient client = new Danbooru("http://danbooru.donmai.us");
    // Retrieve search results.
    final SearchResult page1 = client.search("duck", 0);
    final SearchResult page2 = client.search("duck", 1);

    // Make sure that the results differ.
    assertThat(page1.getImages()).isNotEmpty();
    assertThat(page2.getImages()).isNotEmpty();
    assertThat(page1.getImages()[0].id).isNotEqualTo(page2.getImages()[0].id);
  }
}
