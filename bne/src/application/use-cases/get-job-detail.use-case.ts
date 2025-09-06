import type { JobRepository } from "../../domain/repositories/job-repository.interface"
import type { JobOffer } from "../../domain/entities/job-offer"

export class GetJobDetailUseCase {
  constructor(private jobRepository: JobRepository) {}

  async execute(jobId: string): Promise<JobOffer | null> {
    return await this.jobRepository.getJobById(jobId)
  }
}
