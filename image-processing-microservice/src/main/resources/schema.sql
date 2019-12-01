-- Table: discogs_image_processing_microservice.image

-- DROP TABLE discogs_image_processing_microservice.image;
--
-- PostgreSQL database dump
--

-- Dumped from database version 12.0
-- Dumped by pg_dump version 12.0

-- Started on 2019-10-28 10:42:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16394)
-- Name: discogs_release_microservice_two; Type: SCHEMA; Schema: -; Owner: postgres
--
DROP SCHEMA IF EXISTS discogs_image_processing_microservice CASCADE;

CREATE SCHEMA discogs_image_processing_microservice;


ALTER SCHEMA discogs_image_processing_microservice OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE discogs_image_processing_microservice.image
(
    id bigint NOT NULL,
    original_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    extension character varying(10) COLLATE pg_catalog."default" NOT NULL,
    width integer NOT NULL,
    height integer NOT NULL,
    uri character varying(255) COLLATE pg_catalog."default",
    uploader_id bigint NOT NULL,
    upload_date timestamp without time zone NOT NULL,
    item_id bigint NOT NULL,
    CONSTRAINT image_pkey PRIMARY KEY (id)
);

ALTER TABLE discogs_image_processing_microservice.image OWNER to postgres;

CREATE SEQUENCE discogs_image_processing_microservice.image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE discogs_image_processing_microservice.image_id_seq OWNER TO postgres;

ALTER SEQUENCE discogs_image_processing_microservice.image_id_seq OWNED BY discogs_image_processing_microservice.image.id;

ALTER TABLE ONLY discogs_image_processing_microservice.image ALTER COLUMN id SET DEFAULT nextval('discogs_image_processing_microservice.image_id_seq'::regclass);