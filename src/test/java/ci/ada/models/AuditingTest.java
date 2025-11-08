package ci.ada.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuditingTest {

    private static class AuditingImpl extends Auditing {}

    private final Instant creationDate = Instant.now();
    private final Instant modificationDate = Instant.now().plusSeconds(60);
    private final Instant deletionDate = Instant.now().plusSeconds(120);
    private final String createdBy = "admin";
    private final String modifiedBy = "editor";
    private final String deletionBy = "deleter";
    private final String deletionReason = "obsolete";
    private final String slug = "my-slug";

    private AuditingImpl auditing;

    @BeforeEach
    void setUp() {
        auditing = new AuditingImpl();
    }

    @Test
    void assertThatFieldsAreEquals() {
        auditing.setCreationDate(creationDate);
        auditing.setModificationDate(modificationDate);
        auditing.setDeletionDate(deletionDate);
        auditing.setCreatedBy(createdBy);
        auditing.setModifiedBy(modifiedBy);
        auditing.setDeletionBy(deletionBy);
        auditing.setDeletionReason(deletionReason);
        auditing.setSlug(slug);

        assertThat(auditing).isNotNull()
                .satisfies(a -> assertEquals(creationDate, a.getCreationDate()))
                .satisfies(a -> assertEquals(modificationDate, a.getModificationDate()))
                .satisfies(a -> assertEquals(deletionDate, a.getDeletionDate()))
                .satisfies(a -> assertEquals(createdBy, a.getCreatedBy()))
                .satisfies(a -> assertEquals(modifiedBy, a.getModifiedBy()))
                .satisfies(a -> assertEquals(deletionBy, a.getDeletionBy()))
                .satisfies(a -> assertEquals(deletionReason, a.getDeletionReason()))
                .satisfies(a -> assertEquals(slug, a.getSlug()));
    }
}
