
package org.apereo.cas;

import org.apereo.cas.monitor.CacheHealthIndicatorTests;
import org.apereo.cas.monitor.MemoryHealthIndicatorTests;
import org.apereo.cas.monitor.PoolHealthIndicatorTests;
import org.apereo.cas.monitor.SessionHealthIndicatorTests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

/**
 * This is {@link AllTestsSuite}.
 *
 * @author Misagh Moayyed
 * @since 6.0.0-RC3
 */
@SelectClasses({
    MemoryHealthIndicatorTests.class,
    PoolHealthIndicatorTests.class,
    SessionHealthIndicatorTests.class,
    CacheHealthIndicatorTests.class
})
@RunWith(JUnitPlatform.class)
public class AllTestsSuite {
}
