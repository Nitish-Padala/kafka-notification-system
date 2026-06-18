# Kafka Unified Notification System

## Project Overview
A production-ready notification system with three channels (Email, Push, SMS), powered by Apache Kafka and Spring Boot.

## Tech Stack
- **Java 17**
- **Spring Boot 3.3.3**
- **Apache Kafka** (Dockerized)
- **Maven**
- **IDE:** Eclipse

## Architecture
- **Producer:** Receives API requests and pushes messages to Kafka topics.
- **Consumers:** Listen to Kafka topics and trigger the actual notification delivery.
- **Channels:** Email (JavaMailSender), Push (FCM), SMS (Twilio).

## Project Structure
- `com.unified.notification.config`: Configuration for Kafka and external services.
- `com.unified.notification.controller`: REST endpoints.
- `com.unified.notification.dto`: Data Transfer Objects.
- `com.unified.notification.producer`: Kafka message producers.
- `com.unified.notification.consumer`: Kafka message consumers.
- `com.unified.notification.service`: Business logic for notification delivery.

## Current Progress
- [x] Phase 1: Setup (Environment, Project Creation, Docker-Kafka, GitHub Push)
- [x] Phase 2: Kafka Producer (Completed)
- [x] Phase 3: Notification Delivery Services (Completed - Email flow verified)

## Guidelines
- **Teaching Mode:** Claude provides the code and comprehensive explanations (Why, How, Efficiency, and Alternatives). The user implements the code in the IDE.
- **Commit Policy:** Push to GitHub after every major task completion.
- **Coding Standard:** Use Lombok for boilerplate reduction.
