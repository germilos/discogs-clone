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
DROP SCHEMA IF EXISTS discogs_release_microservice_two CASCADE;

CREATE SCHEMA discogs_release_microservice_two;


ALTER SCHEMA discogs_release_microservice_two OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16422)
-- Name: genre; Type: TABLE; Schema: discogs_release_microservice_twoe; Owner: postgres
--

CREATE TABLE discogs_release_microservice_two.genre (
    name character varying(100) NOT NULL,
    id bigint NOT NULL,
    description character varying(500)
);


ALTER TABLE discogs_release_microservice_two.genre OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16575)
-- Name: genere_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.genere_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.genere_id_seq OWNER TO postgres;

--
-- TOC entry 2956 (class 0 OID 0)
-- Dependencies: 208
-- Name: genere_id_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.genere_id_seq OWNED BY discogs_release_microservice_two.genre.id;