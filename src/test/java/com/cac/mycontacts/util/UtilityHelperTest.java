package com.cac.mycontacts.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UtilityHelperTest {

    @Test
    public void should_validate_valid_url() {
        assertThat(UtilityHelper.isValidUrl("https://vignette.wikia.nocookie.net/simpsons/images/a/af/Tattoo.png/revision/latest/scale-to-width-down/117?cb=20130329093024")).isTrue();
    }

    @Test
    public void should_not_validate_invalid_url() {
        assertThat(UtilityHelper.isValidUrl("www.google.com")).isFalse();
    }
}
