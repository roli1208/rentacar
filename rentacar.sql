PGDMP     *                    z           cars    14.4    14.4     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                        0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16394    cars    DATABASE     d   CREATE DATABASE cars WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Hungarian_Hungary.1250';
    DROP DATABASE cars;
                postgres    false            �            1259    16419    car    TABLE     �   CREATE TABLE public.car (
    id integer NOT NULL,
    name character varying(255),
    price integer NOT NULL,
    active boolean NOT NULL,
    image character varying(255)
);
    DROP TABLE public.car;
       public         heap    postgres    false            �            1259    16395    cars    TABLE     p   CREATE TABLE public.cars (
    name "char",
    available boolean,
    price bigint,
    id integer NOT NULL
);
    DROP TABLE public.cars;
       public         heap    postgres    false            �            1259    16400    cars_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cars_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.cars_id_seq;
       public          postgres    false    209                       0    0    cars_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.cars_id_seq OWNED BY public.cars.id;
          public          postgres    false    210            �            1259    16426    customer    TABLE     W  CREATE TABLE public.customer (
    id integer NOT NULL,
    address character varying(255),
    amount integer NOT NULL,
    carid integer NOT NULL,
    email character varying(255),
    from_date timestamp without time zone,
    name character varying(255),
    phone_number character varying(255),
    to_date timestamp without time zone
);
    DROP TABLE public.customer;
       public         heap    postgres    false            �            1259    16413    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            e           2604    16401    cars id    DEFAULT     b   ALTER TABLE ONLY public.cars ALTER COLUMN id SET DEFAULT nextval('public.cars_id_seq'::regclass);
 6   ALTER TABLE public.cars ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209            �          0    16419    car 
   TABLE DATA           =   COPY public.car (id, name, price, active, image) FROM stdin;
    public          postgres    false    212   ?       �          0    16395    cars 
   TABLE DATA           :   COPY public.cars (name, available, price, id) FROM stdin;
    public          postgres    false    209   *       �          0    16426    customer 
   TABLE DATA           m   COPY public.customer (id, address, amount, carid, email, from_date, name, phone_number, to_date) FROM stdin;
    public          postgres    false    213   R                  0    0    cars_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.cars_id_seq', 1, true);
          public          postgres    false    210                       0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 22, true);
          public          postgres    false    211            i           2606    16423    car car_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.car DROP CONSTRAINT car_pkey;
       public            postgres    false    212            g           2606    16403    cars cars_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.cars DROP CONSTRAINT cars_pkey;
       public            postgres    false    209            k           2606    16432    customer customer_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    213            �   �   x�=��j�@��O��@Ӝ��zk�BA�$�����6���է�fWz93�C����X=,�iDe����1حn��~���2�PWe�׃���({��F�j�e���8�����60m҄,�9��|}X��PRD��K�sX(��B�A"��$c��zP����Y��5G殓X�(��w���HǓ_�won�X_do��=5�rb{x���W�$�.�V�      �      x�+�,�4400�4����� �      �   �  x��S�n�@���������Ir&�Hc�CQ4�Y.>Iq!K�N����s����Rr�#\��I)�� &3�@������0[�;���M�B��4�jzp{}���ҤsFI}FEzd�����/�v������,h8���dB$e 3�|	ɫ�2iH�8՝sY`��S��Ԡ|�>�^���v�jFk����}�;����8�Ƌ�O'(��(GP��'��z8��pl�����JR�6x_��嬁���ġ�XA�d�?��[Z�!C�� w*�󬄉�]�`R�Lc	�}^�����$���Q)�ږO�m�y��>1J?�pܱZ�d��lXdD��C�o�Z�mew�'Z4ee���Dgo&������ m�li.:%
�d�'II4��+'3��/Mekw�Q�#���(�Hx��     