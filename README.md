# Catalogo Restaurantes

<p align="center">
  <img src="https://github.com/machadom01/CatalogoRestaurantes/blob/main/logo1.png?raw=true" width="150" alt="Logo de Catalogo Restaurantes">
</p>

<p align="center">
  <strong>Una aplicación Android nativa desarrollada en Java, enfocada en demostrar una arquitectura de UI robusta y patrones de navegación.</strong>
</p>

<p align="center">
  <!-- ¡IMPRESCINDIBLE! Un GIF vale más que mil palabras. Graba tu app y súbelo aquí. -->
  <img src="https://github.com/machadom01/CatalogoRestaurantes/blob/main/demo1.gif?raw=true" alt="Demo de la App">
</p>

---

## 🎯 Filosofía del Proyecto

Este proyecto no es solo una aplicación, es una **demostración técnica deliberada**. Fue concebido como un ejercicio práctico para dominar los pilares de la construcción de interfaces de usuario y flujos de navegación en el ecosistema tradicional de Android con Java. El enfoque principal fue implementar una experiencia de usuario coherente y predecible, utilizando componentes fundamentales de manera eficiente y escalable.

---

## ✨ Arquitectura y Desglose de Funcionalidades

La aplicación está estructurada en un flujo de tres niveles que demuestra patrones de navegación comunes y la gestión de la interfaz de usuario.

#### 1. Pantalla Principal (`MainActivity`)
*   **Componente Clave:** `ListView` con un `ArrayAdapter` personalizado para mostrar la lista de restaurantes.
*   **Navegación:**
    *   **Menú Contextual:** Al mantener presionado un restaurante, se despliega un `ContextMenu` ("Comidas", "Bebidas", "Complementos"). Esta acción demuestra una **navegación profunda (deep-linking)**, pasando datos a través de un `Intent` para abrir la siguiente pantalla en una pestaña específica.
    *   **Búsqueda:** Un botón de búsqueda permite filtrar la lista de restaurantes en tiempo real.

#### 2. Detalle del Restaurante (`RestaurantDetailActivity`)
*   **Componentes Clave:** `ViewPager` sincronizado con un `TabLayout` para una navegación fluida entre menús.
*   **Modularidad:** Cada pestaña es un `Fragment` independiente (`FoodFragment`, `DrinksFragment`, etc.), cada uno gestionando su propio `ListView` y su propio conjunto de datos. Esto demuestra una **arquitectura modular y la reutilización de layouts**.
*   **Navegación:**
    *   El deslizamiento del `ViewPager` actualiza la selección del `TabLayout` y viceversa, creando una experiencia de usuario intuitiva.
    *   La selección de un platillo navega a la pantalla de detalle.

#### 3. Detalle del Platillo (`MenuItemDetailActivity`)
*   **Componente Clave:** Una vista de detalle estática que muestra más información (imagen, nombre, precio, descripción) del alimento o platillo seleccionado.

#### Estándares de UI Implementados
*   **`Toolbar` Consistente:** Cada `Activity` utiliza una `Toolbar` personalizada para mostrar títulos relevantes.
*   **Navegación Ascendente (Up Navigation):** El botón `Home/Up` está habilitado en todas las pantallas secundarias y su comportamiento padre está correctamente definido en el `AndroidManifest.xml`, adhiriéndose a las guías de diseño de Android.

---

## 🛠️ Stack Tecnológico

*   **Lenguaje:** **Java**
*   **IDE:** Android Studio
*   **Arquitectura de UI:** Android Native (XML Layouts)
*   **Componentes Fundamentales:**
    *   `Activity` y `Fragment` para la gestión del ciclo de vida y la modularidad.
    *   `ViewPager` y `TabLayout` para la navegación por pestañas.
    *   `ListView` con `ArrayAdapter` personalizados.
    *   `ContextMenu` para acciones contextuales.
    *   `Intent` para la comunicación y el paso de datos entre componentes.
    *   `AndroidManifest.xml` para la definición de la estructura y navegación de la app.

---

## 🧠 Desafíos Técnicos y Soluciones Implementadas

Este proyecto me permitió resolver problemas clásicos que solidificaron mi entendimiento de Android.

1.  **El Desafío: Navegación Profunda desde un `ContextMenu` a un `Fragment` Específico**
    *   **Problema:** ¿Cómo lograr que al seleccionar "Bebidas" en el menú contextual, la `RestaurantDetailActivity` no solo se abra, sino que muestre activamente la pestaña y el `Fragment` de "Bebidas"?
    *   **Solución:** Utilicé el método `Intent.putExtra()` para enviar un identificador numérico (el índice de la pestaña) desde la `MainActivity`. En el `onCreate()` de la `RestaurantDetailActivity`, recuperé este índice y lo usé para llamar a `viewPager.setCurrentItem(index)`, posicionando al usuario directamente en el contenido deseado.
    *   **Aprendizaje Clave:** Dominé el paso de datos entre `Activities` como un mecanismo para controlar el estado inicial de la UI, una técnica fundamental para crear flujos de usuario eficientes y contextuales.

2.  **El Desafío: Búsqueda Encapsulada por `Fragment`**
    *   **Problema:** La funcionalidad de búsqueda en la pantalla de detalle debía filtrar únicamente la lista del `Fragment` actualmente visible, sin afectar a las listas de los otros fragmentos en el `ViewPager`.
    *   **Solución:** Implementé la lógica de filtrado del `Adapter` y el `TextWatcher` del campo de búsqueda **dentro de cada clase `Fragment`**. Esto aseguró que cada fragmento fuera una unidad autónoma y responsable de su propia UI y estado de datos.
    *   **Aprendizaje Clave:** Comprendí en la práctica el principio de encapsulación y el ciclo de vida de los `Fragments`, viéndolos como componentes modulares y reutilizables que previenen efectos secundarios no deseados en una arquitectura compleja.

---

## 🚀 Cómo Ejecutar el Proyecto

1.  **Clona el repositorio:**
    ```bash
    git clone https://github.com/machadom01/CatalogoRestaurantes.git
    ```
2.  **Abre en Android Studio:**
    Desde Android Studio, selecciona `File > Open` y navega a la carpeta del proyecto clonado.
3.  **Sincroniza con Gradle:**
    El IDE se encargará de configurar el proyecto.
4.  **Ejecuta la aplicación:**
    Selecciona un emulador o un dispositivo físico y presiona el botón "Run" (▶️).

---

## 🔮 Hoja de Ruta para Mejoras Futuras

Este proyecto sienta las bases para futuras mejoras que demuestran mi conocimiento de las prácticas modernas de Android:

- [ ] **Migrar a `RecyclerView`:** Reemplazar `ListView` para aprovechar la virtualización de vistas, lo que ofrece un rendimiento inmensamente superior con grandes conjuntos de datos.
- [ ] **Adoptar Arquitectura MVVM:** Implementar `ViewModel` y `LiveData` para separar la lógica de la UI, haciendo la app más robusta, testable y resistente a cambios de configuración.
- [ ] **Transición a Kotlin:** Refactorizar el código de Java a Kotlin para aprovechar su sintaxis concisa, seguridad contra nulos y corutinas para la asincronía.
- [ ] **Integrar Jetpack Compose:** Reconstruir la UI con el moderno toolkit declarativo de Google para acelerar el desarrollo y crear interfaces más dinámicas y mantenibles.

