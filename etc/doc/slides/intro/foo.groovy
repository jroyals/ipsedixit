class ConcurrentGirlfriendSynchronizerTest {
    @Arbitrary Girlfriend gf
    @Arbitrary String name
    @ArbitraryNumber(min=18, max=100) int age

    @Before def setup() {
        IpsedixitAnnotations.initArbitraries(this)
    }

    @Test def canSetName() {
        ...
    }

    @Test def canAddGirlfriendToEmptySynchronizer() {
        ...
    }
}
