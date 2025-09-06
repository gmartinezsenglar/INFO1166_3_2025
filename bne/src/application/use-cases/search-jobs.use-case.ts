import type { JobRepository } from "../../domain/repositories/job-repository.interface"
import type { JobOffer, JobSearchCriteria } from "../../domain/entities/job-offer"

export class SearchJobsUseCase {
  constructor(private jobRepository: JobRepository) {}

  async execute(criteria: JobSearchCriteria): Promise<{
    jobs: JobOffer[]
    totalCount: number
    currentPage: number
    totalPages: number
  }> {
    return await this.jobRepository.searchJobs(criteria)
  }
}
