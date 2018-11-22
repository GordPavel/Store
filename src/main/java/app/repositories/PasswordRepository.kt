package app.repositories

import app.models.VKEntity
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface PasswordRepository : Neo4jRepository<VKEntity , Long>