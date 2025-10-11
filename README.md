Tasks Service skeleton generated from monolith.

Notes:
- The original 'tasks' packages were copied from the monolith.
- Minimal placeholder DTOs for IAM.User and Groups.Group were added to allow compilation.
  Replace these with proper clients or remove if you choose to add domain shared libraries.
- The service uses H2 by default for quick startup. Configure a real datasource for production.
- Run with: mvn -f pom.xml spring-boot:run

Next deliverables possible:
- OpenAPI contract extraction (B)
- Flyway scripts for schema (C)
- TaskFacade patch for monolith (D)
- Detailed ops checklist (E)
