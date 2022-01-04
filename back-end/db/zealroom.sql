--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2021-12-27 16:23:51

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 16411)
-- Name: booking; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.booking (
    room_id integer NOT NULL,
    booking_uuid integer NOT NULL,
    user_uuid character varying NOT NULL,
    is_booked boolean NOT NULL,
    check_in date NOT NULL,
    check_out date NOT NULL
);


ALTER TABLE public.booking OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16415)
-- Name: rooms; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rooms (
    capacity integer NOT NULL,
    room_uuid integer NOT NULL,
    room_description character varying(255) NOT NULL,
    room_number character varying(255) NOT NULL
);


ALTER TABLE public.rooms OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16404)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_uuid character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    is_admin boolean NOT NULL,
    session_token character varying(255),
    email character varying(255) NOT NULL,
    pwd character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 3174 (class 2606 OID 16432)
-- Name: booking booking_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY (booking_uuid);


--
-- TOC entry 3176 (class 2606 OID 16425)
-- Name: rooms rooms_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (room_uuid);


--
-- TOC entry 3172 (class 2606 OID 16410)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_uuid);


--
-- TOC entry 3177 (class 2606 OID 16426)
-- Name: booking fk_rooms; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT fk_rooms FOREIGN KEY (room_id) REFERENCES public.rooms(room_uuid);


--
-- TOC entry 3178 (class 2606 OID 16435)
-- Name: booking fk_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT fk_users FOREIGN KEY (user_uuid) REFERENCES public.users(user_uuid) NOT VALID;


-- Completed on 2021-12-27 16:23:51

--
-- PostgreSQL database dump complete
--
