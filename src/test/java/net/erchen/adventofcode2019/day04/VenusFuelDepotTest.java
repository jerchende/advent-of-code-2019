package net.erchen.adventofcode2019.day04;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VenusFuelDepotTest {

    @Test
    void shouldFindValidCodes() {
        assertThat(VenusFuelDepot.validCodes(111111, 111111, false)).containsExactly(111111);
        assertThat(VenusFuelDepot.validCodes(111111, 111112, false)).containsExactly(111111, 111112);
        assertThat(VenusFuelDepot.validCodes(223450, 223450, false)).isEmpty();
        assertThat(VenusFuelDepot.validCodes(123789, 123789, false)).isEmpty();
    }

    @Test
    void shouldFindValidCodesWithoutLargerGroups() {
        assertThat(VenusFuelDepot.validCodes(112233, 112233, true)).containsExactly(112233);
        assertThat(VenusFuelDepot.validCodes(123444, 123444, true)).isEmpty();
        assertThat(VenusFuelDepot.validCodes(111122, 111122, true)).containsExactly(111122);
    }

    @Test
    void shouldFindValidCodes_Solution() {
        assertThat(VenusFuelDepot.validCodes(372304, 847060, false)).hasSize(475);
    }

    @Test
    void shouldFindValidCodesWithoutLargerGroups_Solution() {
        assertThat(VenusFuelDepot.validCodes(372304, 847060, true)).hasSize(297);
    }

}