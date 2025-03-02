import { useState, useEffect } from "react";
import { useAuth0 } from "@auth0/auth0-react";

const Quiz = () => {
  const { user, isAuthenticated, loginWithRedirect } = useAuth0();
  const [questions, setQuestions] = useState([]);
  const [currentQuestion, setCurrentQuestion] = useState(0);
  const [totalScore, setTotalScore] = useState(0);
  const [quizFinished, setQuizFinished] = useState(false);
  const [message, setMessage] = useState("");
  const [answers, setAnswers] = useState([]);

  useEffect(() => {
    const fetchQuestions = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/questions");
        if (!response.ok) throw new Error("Error en backend");
        const data = await response.json();
        setQuestions(data || []);
      } catch (error) {
        console.error("Error al cargar preguntas:", error);
      }
    };
    fetchQuestions();
  }, []);

  useEffect(() => {
    const fetchAnswers = async () => {
      if (questions.length > 0) {
        try {
          const response = await fetch(`http://localhost:8080/api/answers/question/${questions[currentQuestion].id}`);
          if (!response.ok) throw new Error("Error en backend");
          const data = await response.json();
          setAnswers(data || []);
        } catch (error) {
          console.error("Error al cargar respuestas:", error);
        }
      }
    };
    fetchAnswers();
  }, [currentQuestion, questions]);

  const handleAnswer = (score) => {
    const newScore = totalScore + score;
    if (questions.length > 0 && currentQuestion + 1 < questions.length) {
      setTotalScore(newScore);
      setCurrentQuestion(currentQuestion + 1);
    } else {
      setTotalScore(newScore);
      setQuizFinished(true);
      sendData(newScore);
    }
  };

  const sendData = async (score) => {
    if (!isAuthenticated || !user?.email) {
      setMessage("Debes iniciar sesión para guardar tu huella de carbono.");
      return;
    }

    const record = {
      personalFootPrint: score,
      date: new Date().toISOString().split("T")[0],
      user: { email: user.email },
    };

    try {
      const response = await fetch("http://localhost:8080/api/records", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(record),
      });

      if (!response.ok) throw new Error("Error en backend");

      setMessage("Huella de carbono guardada con éxito ✅");
      if (totalScore > 0 && totalScore <= 10) {
        setMessage("Estás haciendo un esfuerzo significativo para reducir tu impacto ambiental. Tus hábitos son sostenibles y responsables con el planeta.");
      }else if (totalScore >= 11 && totalScore <= 20) {
        setMessage("Tu huella de carbono es promedio. Hay áreas donde puedes mejorar, como reducir el uso del coche, consumir menos carne o ser más eficiente con la energía.");
      }else if (totalScore >= 21 && totalScore <= 30) {
        setMessage("Tu impacto ambiental es alto. Requiere una mayor atención a tu consumo de energía, transporte y hábitos de consumo. Considera tomar medidas para reducir tu huella, como cambiar a transporte público, reducir el uso de aviones o adoptar un estilo de vida más sostenible.");
      }else if (totalScore >= 31 && totalScore <= 40) {
        setMessage("Tienes una huella de carbono considerablemente alta. Necesitas realizar ajustes importantes en tu estilo de vida, como cambiar tus hábitos de consumo de energía, transporte y alimentación para reducir significativamente tu impacto ambiental.");
      }
    } catch (error) {
      setMessage("❌ No se pudo guardar tu huella de carbono.");
      console.error("Error:", error);
    }
  };

  return (
    
    <div className="container">
        <header>
          <h1>Calculadora de Huella de Carbono</h1>
          <p>Calcula tu impacto ambiental y descubre cómo reducirlo</p>
        </header>
        <div className="calculator-container">
        <div className="tabs">
          <button className="tab-btn active" data-tab="Calcula tu huella">
            
            Calcula tu huella de Carbono
            
          </button>
        

          </div>
          <div className="p-6 max-w-md mx-auto bg-white rounded-xl shadow-md space-y-4">
    <header>
    
    <button className="tab-btn activo" data-tab="Calcula tu huella"><b> Responda con Sinceridad "Buena Suerte"</b></button>
    <br/><br/><br/>
      {!isAuthenticated ? (
        <button
          onClick={() => loginWithRedirect()}
          className="px-4 py-2 bg-blue-500 text-white rounded-lg"
        >
          Iniciar sesión con Auth0
        </button>
      ) : questions.length === 0 ? (
        <p>Cargando preguntas...</p>
      ) : !quizFinished ? (
        <div>
          <h3 className="text-lg font-semibold">
            {questions[currentQuestion]?.question || "Pregunta no disponible"}
          </h3>
          <div className="mt-3">
            {answers.length > 0 ? (
              answers.map((option) => (
                <button
                  key={option.id}
                  onClick={() => handleAnswer(option.score)}
                  className="block w-full px-4 py-2 my-2 bg-green-500 text-black rounded-lg hover:bg-green-600"
                >
                  {option.text}
                </button>
              ))
            ) : (
              <p>Opciones no disponibles</p>
            )}
          </div>
        </div>
      ) : (
        <div className="text-center">
          <h3 className="text-lg font-semibold">
            ¡Quiz terminado! Tu puntuación: {totalScore} pts
          </h3>
          <p className="mt-2 text-sm text-gray-600">{message}</p>
        </div>
      )}
    </header>
    </div>   

        {/* Agregar el botón de "Volver a la página principal" */}
  
        </div>
    
    
    </div>
    
  );
};

export default Quiz;




