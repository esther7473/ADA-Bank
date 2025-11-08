package ci.ada.services.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuditingDTOTest {

    private final Instant creationDate = Instant.now();
    private final Instant modificationDate = Instant.now().plusSeconds(60);
    private final Instant deletionDate = Instant.now().plusSeconds(120);
    private final String createdBy = "admin";
    private final String modifiedBy = "editor";
    private final String deletionBy = "deleter";
    private final String deletionReason = "obsolete";
    private final String slug = "my-slug";

    private AuditingDTO auditingDTO;

    @BeforeEach
    void setUp() {
        auditingDTO = new AuditingDTO();
    }

    @Test
    void assertThatFieldsAreEquals() {
        auditingDTO.setCreationDate(creationDate);
        auditingDTO.setModificationDate(modificationDate);
        auditingDTO.setDeletionDate(deletionDate);
        auditingDTO.setCreatedBy(createdBy);
        auditingDTO.setModifiedBy(modifiedBy);
        auditingDTO.setDeletionBy(deletionBy);
        auditingDTO.setDeletionReason(deletionReason);
        auditingDTO.setSlug(slug);

        assertThat(auditingDTO).isNotNull()
                .satisfies(a -> assertEquals(creationDate, a.getCreationDate()))
                .satisfies(a -> assertEquals(modificationDate, a.getModificationDate()))
                .satisfies(a -> assertEquals(deletionDate, a.getDeletionDate()))
                .satisfies(a -> assertEquals(createdBy, a.getCreatedBy()))
                .satisfies(a -> assertEquals(modifiedBy, a.getModifiedBy()))
                .satisfies(a -> assertEquals(deletionBy, a.getDeletionBy()))
                .satisfies(a -> assertEquals(deletionReason, a.getDeletionReason()))
                .satisfies(a -> assertEquals(slug, a.getSlug()));

        AuditingDTO other = new AuditingDTO(creationDate, modificationDate, deletionDate,createdBy,
                modifiedBy, deletionBy, deletionReason, slug);

        assertThat(other).isNotNull()
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
