// import React, { useEffect, useState } from "react";
// import { useAuth0 } from "@auth0/auth0-react";

// export const Questionnaire = () => {
//   const [questions, setQuestions] = useState([]);
//   const [answers, setAnswers] = useState({});
//   const [result, setResult] = useState(null);
//   const { getAccessTokenSilently } = useAuth0();

//   // Obtener las preguntas del backend
//   useEffect(() => {
    
//     const fetchQuestions = async () => {
//       try {
//         const token = await getAccessTokenSilently();
//         console.log(token)
//         const response = await fetch("http://localhost:8080/api/questions", {
//           headers: {
//             Authorization: `Bearer ${token}`,
//           },
//         });
//         console.log(token)
//         const data = await response.json();
//         console.log("Respuesta de preguntas:", data);
//         setQuestions(data);
//       } catch (error) {
//         console.error("Error al obtener las preguntas", error);
//       }
//     };
//     fetchQuestions();
//   }, [getAccessTokenSilently]);

//   // Manejar el cambio de respuesta para cada pregunta
//   const handleChange = (questionId, optionId) => {
//     setAnswers(prev => ({ ...prev, [questionId]: optionId }));
//   };

//   // Enviar respuestas al backend para calcular la huella
//   const handleSubmit = async () => {
//     // Convertir el objeto answers al formato esperado por el backend (SubmitAnswersDTO)
//     const submitData = {
//       answers: Object.keys(answers).map(questionId => ({
//         questionId: parseInt(questionId),
//         optionId: answers[questionId]
//       }))
//     };

//     try {
//       const token = await getAccessTokenSilently();
//       const response = await fetch("http://localhost:8080/api/results", {
//         method: "POST",
//         headers: {
//           "Content-Type": "application/json",
//           Authorization: `Bearer ${token}`,
//         },
//         body: JSON.stringify(submitData),
//       });
//       const data = await response.json();
//       setResult(data.totalScore);
//     } catch (error) {
//       console.error("Error al enviar las respuestas", error);
//     }
//   };



import React, { useEffect, useState } from "react";
import { useAuth0 } from "@auth0/auth0-react";
import NeighborhoodServices from "../services/NeighborhoodServices";

export const Neighborhood = () => {
  
  // const { getAccessTokenSilently } = useAuth0();
  const [neighborhood, setNeighborhood] = useState([]);

  useEffect(() => {
    NeighborhoodServices.getAllNeighborhoods().then(response => {
      setNeighborhood(response.data);
      console.log(response.data);
    }).catch(error => {
      console.log(error);
    })

  },[])

  return (
    <div className="container">
      <h2 className="text-center">Lista de barrios</h2>
      <table>
        <thead>
          <th>Id</th>
          <th>name</th>
          <th>city</th>
          <th>totalFootPrint</th>
        </thead>
        <tbody>
          {
            neighborhood.map(
              neighborhood =>
                <tr key={ neighborhood.id }>
                  <td>{ neighborhood.name }</td>
                  <td>{ neighborhood.city }</td>
                  <td>{ neighborhood.totalFootPrint }</td>
                </tr> 
            )
          }
        </tbody>

      </table>
    </div>
  )
}


export default Neighborhood;