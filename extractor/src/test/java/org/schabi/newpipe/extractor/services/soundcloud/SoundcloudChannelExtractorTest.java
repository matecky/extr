package org.schabi.newpipe.extractor.services.soundcloud;

import org.junit.BeforeClass;
import org.junit.Test;
import org.schabi.newpipe.DownloaderTestImpl;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.channel.ChannelExtractor;
import org.schabi.newpipe.extractor.channel.ChannelTabExtractor;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.services.BaseChannelExtractorTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.schabi.newpipe.extractor.ExtractorAsserts.assertEmpty;
import static org.schabi.newpipe.extractor.ExtractorAsserts.assertIsSecureUrl;
import static org.schabi.newpipe.extractor.ServiceList.SoundCloud;
import static org.schabi.newpipe.extractor.services.DefaultTests.defaultTestGetPageInNewExtractor;
import static org.schabi.newpipe.extractor.services.DefaultTests.defaultTestMoreItems;
import static org.schabi.newpipe.extractor.services.DefaultTests.defaultTestRelatedItems;

/**
 * Test for {@link SoundcloudChannelExtractor}
 */
public class SoundcloudChannelExtractorTest {
    public static class LilUzi implements BaseChannelExtractorTest {
        private static SoundcloudChannelExtractor extractor;

        @BeforeClass
        public static void setUp() throws Exception {
            NewPipe.init(DownloaderTestImpl.getInstance());
            extractor = (SoundcloudChannelExtractor) SoundCloud
                    .getChannelExtractor("http://soundcloud.com/liluzivert/sets");
            extractor.fetchPage();
        }

        /*//////////////////////////////////////////////////////////////////////////
        // Extractor
        //////////////////////////////////////////////////////////////////////////*/

        @Test
        public void testServiceId() {
            assertEquals(SoundCloud.getServiceId(), extractor.getServiceId());
        }

        @Test
        public void testName() {
            assertEquals("LIL UZI VERT", extractor.getName());
        }

        @Test
        public void testId() {
            assertEquals("10494998", extractor.getId());
        }

        @Test
        public void testUrl() throws ParsingException {
            assertEquals("https://soundcloud.com/liluzivert", extractor.getUrl());
        }

        @Test
        public void testOriginalUrl() throws ParsingException {
            assertEquals("http://soundcloud.com/liluzivert/sets", extractor.getOriginalUrl());
        }

        /*//////////////////////////////////////////////////////////////////////////
        // ListExtractor
        //////////////////////////////////////////////////////////////////////////*/

        @Test
        public void testRelatedItems() throws Exception {
            for (ChannelTabExtractor tab : extractor.getTabs()) {
                defaultTestRelatedItems(tab);
            }
        }

        @Test
        public void testMoreRelatedItems() throws Exception {
            for (ChannelTabExtractor tab : extractor.getTabs()) {
                defaultTestMoreItems(tab);
            }
        }

        /*//////////////////////////////////////////////////////////////////////////
        // ChannelExtractor
        //////////////////////////////////////////////////////////////////////////*/

        @Test
        public void testDescription() {
            assertNotNull(extractor.getDescription());
        }

        @Test
        public void testAvatarUrl() {
            assertIsSecureUrl(extractor.getAvatarUrl());
        }

        @Test
        public void testBannerUrl() {
            assertIsSecureUrl(extractor.getBannerUrl());
        }

        @Test
        public void testFeedUrl() {
            assertEmpty(extractor.getFeedUrl());
        }

        @Test
        public void testSubscriberCount() {
            assertTrue("Wrong subscriber count", extractor.getSubscriberCount() >= 1e6);
        }
    }

    public static class DubMatix implements BaseChannelExtractorTest {
        private static SoundcloudChannelExtractor extractor;

        @BeforeClass
        public static void setUp() throws Exception {
            NewPipe.init(DownloaderTestImpl.getInstance());
            extractor = (SoundcloudChannelExtractor) SoundCloud
                    .getChannelExtractor("https://soundcloud.com/dubmatix");
            extractor.fetchPage();
        }

        /*//////////////////////////////////////////////////////////////////////////
        // Additional Testing
        //////////////////////////////////////////////////////////////////////////*/

        @Test
        public void testGetPageInNewExtractor() throws Exception {
            for (int i = 0; i < extractor.getTabs().size(); i++) {
                final ChannelExtractor newExtractor = SoundCloud.getChannelExtractor(extractor.getUrl());
                newExtractor.fetchPage();
                defaultTestGetPageInNewExtractor(extractor.getTabs().get(i), newExtractor.getTabs().get(i));
            }
        }

        /*//////////////////////////////////////////////////////////////////////////
        // Extractor
        //////////////////////////////////////////////////////////////////////////*/

        @Test
        public void testServiceId() {
            assertEquals(SoundCloud.getServiceId(), extractor.getServiceId());
        }

        @Test
        public void testName() {
            assertEquals("dubmatix", extractor.getName());
        }

        @Test
        public void testId() {
            assertEquals("542134", extractor.getId());
        }

        @Test
        public void testUrl() throws ParsingException {
            assertEquals("https://soundcloud.com/dubmatix", extractor.getUrl());
        }

        @Test
        public void testOriginalUrl() throws ParsingException {
            assertEquals("https://soundcloud.com/dubmatix", extractor.getOriginalUrl());
        }

        /*//////////////////////////////////////////////////////////////////////////
        // ListExtractor
        //////////////////////////////////////////////////////////////////////////*/

        @Test
        public void testRelatedItems() throws Exception {
            for (ChannelTabExtractor tab : extractor.getTabs()) {
                defaultTestRelatedItems(tab);
            }
        }

        @Test
        public void testMoreRelatedItems() throws Exception {
            for (ChannelTabExtractor tab : extractor.getTabs()) {
                defaultTestMoreItems(tab);
            }
        }

        /*//////////////////////////////////////////////////////////////////////////
        // ChannelExtractor
        //////////////////////////////////////////////////////////////////////////*/

        @Test
        public void testDescription() {
            assertNotNull(extractor.getDescription());
        }

        @Test
        public void testAvatarUrl() {
            assertIsSecureUrl(extractor.getAvatarUrl());
        }

        @Test
        public void testBannerUrl() {
            assertIsSecureUrl(extractor.getBannerUrl());
        }

        @Test
        public void testFeedUrl() {
            assertEmpty(extractor.getFeedUrl());
        }

        @Test
        public void testSubscriberCount() {
            assertTrue("Wrong subscriber count", extractor.getSubscriberCount() >= 2e6);
        }
    }
}
