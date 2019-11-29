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


--
-- TOC entry 206 (class 1259 OID 16442)
-- Name: image; Type: TABLE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE TABLE discogs_release_microservice_two.image (
    id bigint NOT NULL,
    upload_date timestamp WITHOUT TIME ZONE NOT NULL,
    item_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE discogs_release_microservice_two.image OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16567)
-- Name: image_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.image_id_seq OWNER TO postgres;

--
-- TOC entry 2957 (class 0 OID 0)
-- Dependencies: 207
-- Name: image_id_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.image_id_seq OWNED BY discogs_release_microservice_two.image.id;


--
-- TOC entry 216 (class 1259 OID 16630)
-- Name: image_item_fk_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.image_item_fk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.image_item_fk_seq OWNER TO postgres;

--
-- TOC entry 2958 (class 0 OID 0)
-- Dependencies: 216
-- Name: image_item_fk_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.image_item_fk_seq OWNED BY discogs_release_microservice_two.image.item_id;


--
-- TOC entry 222 (class 1259 OID 16693)
-- Name: image_user_fk_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.image_user_fk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.image_user_fk_seq OWNER TO postgres;

--
-- TOC entry 2959 (class 0 OID 0)
-- Dependencies: 222
-- Name: image_user_fk_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.image_user_fk_seq OWNED BY discogs_release_microservice_two.image.user_id;


--
-- TOC entry 211 (class 1259 OID 16605)
-- Name: item; Type: TABLE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE TABLE discogs_release_microservice_two.item (
    name character varying(150) NOT NULL,
    date_added date NOT NULL DEFAULT CURRENT_DATE,
    date_last_changed date NOT NULL DEFAULT CURRENT_DATE,
    uri character varying(200),
    format character varying(200),
    country character varying(56),
    notes character varying(200),
    released character varying(20),
    profile character varying(200),
    contact_info character varying(200),
    id bigint NOT NULL,
    label_id bigint,
    artist_id bigint,
    item_type character varying(10) NOT NULL,
    unique(name, item_type)
);

ALTER TABLE discogs_release_microservice_two.item OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16603)
-- Name: item_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.item_id_seq
    START WITH 39
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.item_id_seq OWNER TO postgres;

--
-- TOC entry 2960 (class 0 OID 0)
-- Dependencies: 210
-- Name: item_id_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.item_id_seq OWNED BY discogs_release_microservice_two.item.id;



--
-- TOC entry 221 (class 1259 OID 16675)
-- Name: item_user; Type: TABLE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE TABLE discogs_release_microservice_two.item_user (
    id bigint NOT NULL,
    item_id bigint NOT NULL,
    user_id bigint NOT NULL,
    contribution_date timestamp WITHOUT TIME ZONE NOT NULL
--    contribution_date date NOT NULL
);


ALTER TABLE discogs_release_microservice_two.item_user OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16669)
-- Name: item_user_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.item_user_id_seq
    START WITH 39
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.item_user_id_seq OWNER TO postgres;

--
-- TOC entry 2962 (class 0 OID 0)
-- Dependencies: 218
-- Name: item_user_id_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.item_user_id_seq OWNED BY discogs_release_microservice_two.item_user.id;


--
-- TOC entry 219 (class 1259 OID 16671)
-- Name: item_user_item_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.item_user_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.item_user_item_id_seq OWNER TO postgres;

--
-- TOC entry 2963 (class 0 OID 0)
-- Dependencies: 219
-- Name: item_user_item_id_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.item_user_item_id_seq OWNED BY discogs_release_microservice_two.item_user.item_id;


--
-- TOC entry 220 (class 1259 OID 16673)
-- Name: item_user_user_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.item_user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.item_user_user_id_seq OWNER TO postgres;

--
-- TOC entry 2964 (class 0 OID 0)
-- Dependencies: 220
-- Name: item_user_user_id_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.item_user_user_id_seq OWNED BY discogs_release_microservice_two.item_user.user_id;


--
-- TOC entry 226 (class 1259 OID 16711)
-- Name: release_genre; Type: TABLE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE TABLE discogs_release_microservice_two.release_genre (
    id bigint NOT NULL,
    release_id bigint NOT NULL,
    genre_id bigint NOT NULL
);


ALTER TABLE discogs_release_microservice_two.release_genre OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16709)
-- Name: release_genere_genere_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.release_genere_genere_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.release_genere_genere_id_seq OWNER TO postgres;

--
-- TOC entry 2965 (class 0 OID 0)
-- Dependencies: 225
-- Name: release_genere_genere_id_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.release_genere_genere_id_seq OWNED BY discogs_release_microservice_two.release_genre.genre_id;


--
-- TOC entry 223 (class 1259 OID 16705)
-- Name: release_genere_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.release_genere_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.release_genere_id_seq OWNER TO postgres;

--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 223
-- Name: release_genere_id_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.release_genere_id_seq OWNED BY discogs_release_microservice_two.release_genre.id;


--
-- TOC entry 224 (class 1259 OID 16707)
-- Name: release_genere_release_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.release_genere_release_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.release_genere_release_id_seq OWNER TO postgres;

--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 224
-- Name: release_genere_release_id_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.release_genere_release_id_seq OWNED BY discogs_release_microservice_two.release_genre.release_id;


--
-- TOC entry 228 (class 1259 OID 16731)
-- Name: release_style; Type: TABLE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE TABLE discogs_release_microservice_two.release_style (
    id bigint NOT NULL,
    release_id bigint NOT NULL,
    style_id bigint NOT NULL
);


ALTER TABLE discogs_release_microservice_two.release_style OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16729)
-- Name: release_style_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.release_style_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.release_style_id_seq OWNER TO postgres;

--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 227
-- Name: release_style_id_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.release_style_id_seq OWNED BY discogs_release_microservice_two.release_style.id;


--
-- TOC entry 204 (class 1259 OID 16427)
-- Name: style; Type: TABLE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE TABLE discogs_release_microservice_two.style (
    name character varying(100) NOT NULL,
    id bigint NOT NULL,
    genere_id bigint NOT NULL,
    description character varying(500)
);


ALTER TABLE discogs_release_microservice_two.style OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16594)
-- Name: style_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.style_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.style_id_seq OWNER TO postgres;

--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 209
-- Name: style_id_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.style_id_seq OWNED BY discogs_release_microservice_two.style.id;


--
-- TOC entry 213 (class 1259 OID 16616)
-- Name: track; Type: TABLE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE TABLE discogs_release_microservice_two.track (
    id bigint NOT NULL,
    title character varying(200) NOT NULL,
    duration character varying(10) NOT NULL,
    "position" character varying(10) NOT NULL,
    release_id bigint NOT NULL
);


ALTER TABLE discogs_release_microservice_two.track OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16614)
-- Name: track_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.track_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.track_id_seq OWNER TO postgres;

--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 212
-- Name: track_id_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.track_id_seq OWNED BY discogs_release_microservice_two.track.id;


--
-- TOC entry 217 (class 1259 OID 16657)
-- Name: track_release_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.track_release_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.track_release_id_seq OWNER TO postgres;

--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 217
-- Name: track_release_id_seq; Type: SEQUENCE OWNED BY; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER SEQUENCE discogs_release_microservice_two.track_release_id_seq OWNED BY discogs_release_microservice_two.track.release_id;


--
-- TOC entry 231 (class 1259 OID 24616)
-- Name: user_id_seq; Type: SEQUENCE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE SEQUENCE discogs_release_microservice_two.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discogs_release_microservice_two.user_id_seq OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16437)
-- Name: user; Type: TABLE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE TABLE discogs_release_microservice_two."user" (
    id bigint DEFAULT nextval('discogs_release_microservice_two.user_id_seq'::regclass) NOT NULL,
    username character varying(100) NOT NULL
);


ALTER TABLE discogs_release_microservice_two."user" OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 24625)
-- Name: databasechangeloglock; Type: TABLE; Schema: discogs_release_microservice_two; Owner: postgres
--

CREATE TABLE discogs_release_microservice_two.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE discogs_release_microservice_two.databasechangeloglock OWNER TO postgres;

ALTER TABLE discogs_release_microservice_two.item OWNER TO postgres;

--
-- TOC entry 2770 (class 2604 OID 16577)
-- Name: genre id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.genre ALTER COLUMN id SET DEFAULT nextval('discogs_release_microservice_two.genere_id_seq'::regclass);


--
-- TOC entry 2773 (class 2604 OID 16569)
-- Name: image id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.image ALTER COLUMN id SET DEFAULT nextval('discogs_release_microservice_two.image_id_seq'::regclass);


--
-- TOC entry 2774 (class 2604 OID 16632)
-- Name: image item_id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.image ALTER COLUMN item_id SET DEFAULT nextval('discogs_release_microservice_two.image_item_fk_seq'::regclass);


--
-- TOC entry 2775 (class 2604 OID 16695)
-- Name: image user_id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.image ALTER COLUMN user_id SET DEFAULT nextval('discogs_release_microservice_two.image_user_fk_seq'::regclass);


--
-- TOC entry 2776 (class 2604 OID 16608)
-- Name: item id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.item ALTER COLUMN id SET DEFAULT nextval('discogs_release_microservice_two.item_id_seq'::regclass);

--
-- TOC entry 2780 (class 2604 OID 16678)
-- Name: item_user id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.item_user ALTER COLUMN id SET DEFAULT nextval('discogs_release_microservice_two.item_user_id_seq'::regclass);


--
-- TOC entry 2781 (class 2604 OID 16679)
-- Name: item_user item_id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.item_user ALTER COLUMN item_id SET DEFAULT nextval('discogs_release_microservice_two.item_user_item_id_seq'::regclass);


--
-- TOC entry 2782 (class 2604 OID 16680)
-- Name: item_user user_id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.item_user ALTER COLUMN user_id SET DEFAULT nextval('discogs_release_microservice_two.item_user_user_id_seq'::regclass);


--
-- TOC entry 2783 (class 2604 OID 16714)
-- Name: release_genre id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.release_genre ALTER COLUMN id SET DEFAULT nextval('discogs_release_microservice_two.release_genere_id_seq'::regclass);


--
-- TOC entry 2784 (class 2604 OID 16715)
-- Name: release_genre release_id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.release_genre ALTER COLUMN release_id SET DEFAULT nextval('discogs_release_microservice_two.release_genere_release_id_seq'::regclass);


--
-- TOC entry 2785 (class 2604 OID 16716)
-- Name: release_genre genre_id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.release_genre ALTER COLUMN genre_id SET DEFAULT nextval('discogs_release_microservice_two.release_genere_genere_id_seq'::regclass);


--
-- TOC entry 2786 (class 2604 OID 16734)
-- Name: release_style id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.release_style ALTER COLUMN id SET DEFAULT nextval('discogs_release_microservice_two.release_style_id_seq'::regclass);


--
-- TOC entry 2771 (class 2604 OID 16596)
-- Name: style id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.style ALTER COLUMN id SET DEFAULT nextval('discogs_release_microservice_two.style_id_seq'::regclass);


--
-- TOC entry 2777 (class 2604 OID 16619)
-- Name: track id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.track ALTER COLUMN id SET DEFAULT nextval('discogs_release_microservice_two.track_id_seq'::regclass);


--
-- TOC entry 2778 (class 2604 OID 16659)
-- Name: track release_id; Type: DEFAULT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.track ALTER COLUMN release_id SET DEFAULT nextval('discogs_release_microservice_two.track_release_id_seq'::regclass);


--
-- TOC entry 2788 (class 2606 OID 16582)
-- Name: genre genere_pkey; Type: CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.genre
    ADD CONSTRAINT genere_pkey PRIMARY KEY (id);


--
-- TOC entry 2794 (class 2606 OID 16574)
-- Name: image image_pkey; Type: CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- TOC entry 2796 (class 2606 OID 16613)
-- Name: item item_pkey; Type: CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);



--
-- TOC entry 2802 (class 2606 OID 16682)
-- Name: item_user item_user_pkey; Type: CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.item_user
    ADD CONSTRAINT item_user_pkey PRIMARY KEY (id);


--
-- TOC entry 2804 (class 2606 OID 16718)
-- Name: release_genre release_genere_pkey; Type: CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.release_genre
    ADD CONSTRAINT release_genere_pkey PRIMARY KEY (id);


--
-- TOC entry 2806 (class 2606 OID 16736)
-- Name: release_style release_style_pkey; Type: CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.release_style
    ADD CONSTRAINT release_style_pkey PRIMARY KEY (id);


--
-- TOC entry 2790 (class 2606 OID 16601)
-- Name: style style_pkey; Type: CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.style
    ADD CONSTRAINT style_pkey PRIMARY KEY (id);


--
-- TOC entry 2798 (class 2606 OID 16621)
-- Name: track track_pkey; Type: CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.track
    ADD CONSTRAINT track_pkey PRIMARY KEY (id);


--
-- TOC entry 2792 (class 2606 OID 16441)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 2810 (class 2606 OID 24629)
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- TOC entry 2815 (class 2606 OID 16753)
-- Name: item artist_fkey; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.item
    ADD CONSTRAINT artist_fkey FOREIGN KEY (artist_id) REFERENCES discogs_release_microservice_two.item(id) NOT VALID;


--
-- TOC entry 2820 (class 2606 OID 16724)
-- Name: release_genre genere_fkey; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.release_genre
    ADD CONSTRAINT genere_fkey FOREIGN KEY (genre_id) REFERENCES discogs_release_microservice_two.genre(id) NOT VALID;


--
-- TOC entry 2811 (class 2606 OID 16758)
-- Name: style genere_fkey; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.style
    ADD CONSTRAINT genere_fkey FOREIGN KEY (genere_id) REFERENCES discogs_release_microservice_two.genre(id) NOT VALID;


--
-- TOC entry 2812 (class 2606 OID 16637)
-- Name: image item_fkey; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.image
    ADD CONSTRAINT item_fkey FOREIGN KEY (item_id) REFERENCES discogs_release_microservice_two.item(id) NOT VALID;


--
-- TOC entry 2817 (class 2606 OID 16683)
-- Name: item_user item_fkey; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.item_user
    ADD CONSTRAINT item_fkey FOREIGN KEY (item_id) REFERENCES discogs_release_microservice_two.item(id) NOT VALID;


--
-- TOC entry 2814 (class 2606 OID 16748)
-- Name: item label_fkey; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.item
    ADD CONSTRAINT label_fkey FOREIGN KEY (label_id) REFERENCES discogs_release_microservice_two.item(id) NOT VALID;


--
-- TOC entry 2816 (class 2606 OID 16664)
-- Name: track release_fkey; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.track
    ADD CONSTRAINT release_fkey FOREIGN KEY (release_id) REFERENCES discogs_release_microservice_two.item(id) NOT VALID;


--
-- TOC entry 2819 (class 2606 OID 16719)
-- Name: release_genre release_fkey; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.release_genre
    ADD CONSTRAINT release_fkey FOREIGN KEY (release_id) REFERENCES discogs_release_microservice_two.item(id) NOT VALID;


--
-- TOC entry 2821 (class 2606 OID 16738)
-- Name: release_style release_fkey; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.release_style
    ADD CONSTRAINT release_fkey FOREIGN KEY (release_id) REFERENCES discogs_release_microservice_two.item(id) NOT VALID;


--
-- TOC entry 2822 (class 2606 OID 16743)
-- Name: release_style style_fkey; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.release_style
    ADD CONSTRAINT style_fkey FOREIGN KEY (style_id) REFERENCES discogs_release_microservice_two.style(id) NOT VALID;


--
-- TOC entry 2818 (class 2606 OID 16688)
-- Name: item_user user_fkey; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.item_user
    ADD CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES discogs_release_microservice_two."user"(id) NOT VALID;


--
-- TOC entry 2813 (class 2606 OID 16700)
-- Name: image user_fkey; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.image
    ADD CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES discogs_release_microservice_two."user"(id) NOT VALID;


--
-- TOC entry 2824 (class 2606 OID 24611)
-- Name: item fkbbexflnu0fhhy68cnj5pqsyws; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.item
    ADD CONSTRAINT fkbbexflnu0fhhy68cnj5pqsyws FOREIGN KEY (label_id) REFERENCES discogs_release_microservice_two.item(id);


--
-- TOC entry 2823 (class 2606 OID 24606)
-- Name: item fkbxr36xqhk4ipik2vh5k4kbr6r; Type: FK CONSTRAINT; Schema: discogs_release_microservice_two; Owner: postgres
--

ALTER TABLE ONLY discogs_release_microservice_two.item
    ADD CONSTRAINT fkbxr36xqhk4ipik2vh5k4kbr6r FOREIGN KEY (artist_id) REFERENCES discogs_release_microservice_two.item(id);


-- Completed on 2019-10-28 10:42:47

--
-- PostgreSQL database dump complete
--

