import Login from './login/Login';
import Game from './game/Game';
import Results from './results/Results';

export const routes = [
    {
        element: <Login />,
        path: "/"
    },
    {
        element: <Game />,
        path: "/play"
    },
    {
        element: <Results />,
        path: "/results"
    }
]