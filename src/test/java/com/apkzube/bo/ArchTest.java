package com.apkzube.bo;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.apkzube.bo");

        noClasses()
            .that()
            .resideInAnyPackage("com.apkzube.bo.service..")
            .or()
            .resideInAnyPackage("com.apkzube.bo.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.apkzube.bo.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
