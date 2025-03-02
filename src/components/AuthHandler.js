import { useEffect } from "react";
import { useAuth0 } from "@auth0/auth0-react";

const AuthHandler = () => {
  const { user, isAuthenticated, getAccessTokenSilently } = useAuth0();

  useEffect(() => {
    if (isAuthenticated && user?.email) {
      registerUser(user.email);
    }
  }, [isAuthenticated, user]);

  const registerUser = async (email) => {
    try {
      const token = await getAccessTokenSilently();
      const response = await fetch("http://localhost:8080/api/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ email }),
      });

      if (!response.ok) {
        console.error("Error registrando usuario");
      }
    } catch (error) {
      console.error("Error en el registro:", error);
    }
  };

  return null; // No renderiza nada, solo maneja la autenticación
};

export default AuthHandler;
