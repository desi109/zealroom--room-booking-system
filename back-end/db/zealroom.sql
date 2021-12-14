PGDMP                         y            zealroom    13.5    13.5     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16571    zealroom    DATABASE     i   CREATE DATABASE zealroom WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Bulgarian_Bulgaria.1251';
    DROP DATABASE zealroom;
                postgres    false            �            1259    16615    booking    TABLE     �   CREATE TABLE public.booking (
    "checkIn" date NOT NULL,
    "checkOut" date NOT NULL,
    "personsNum" integer NOT NULL,
    "isBooked" boolean DEFAULT false NOT NULL
);
    DROP TABLE public.booking;
       public         heap    postgres    false            �            1259    16591    rooms    TABLE     �   CREATE TABLE public.rooms (
    room_uuid character varying(255) NOT NULL,
    "roomNumber" character varying(255) NOT NULL,
    capacity integer NOT NULL,
    "roomDescription" character varying(255) NOT NULL
);
    DROP TABLE public.rooms;
       public         heap    postgres    false            �            1259    16580    users    TABLE     I  CREATE TABLE public.users (
    user_uuid character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    is_admin boolean NOT NULL,
    session_token character varying(255),
    email character varying(255) NOT NULL,
    pwd character varying(255) NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �          0    16615    booking 
   TABLE DATA           R   COPY public.booking ("checkIn", "checkOut", "personsNum", "isBooked") FROM stdin;
    public          postgres    false    202   �       �          0    16591    rooms 
   TABLE DATA           U   COPY public.rooms (room_uuid, "roomNumber", capacity, "roomDescription") FROM stdin;
    public          postgres    false    201          �          0    16580    users 
   TABLE DATA           f   COPY public.users (user_uuid, first_name, last_name, is_admin, session_token, email, pwd) FROM stdin;
    public          postgres    false    200   3       0           2606    16620    booking booking_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY ("isBooked");
 >   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_pkey;
       public            postgres    false    202            .           2606    16622    rooms rooms_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (room_uuid);
 :   ALTER TABLE ONLY public.rooms DROP CONSTRAINT rooms_pkey;
       public            postgres    false    201            ,           2606    16587    users users_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_uuid);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    200            �      x������ � �      �      x������ � �      �      x������ � �     