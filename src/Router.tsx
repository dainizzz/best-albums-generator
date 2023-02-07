import Login from './login/Login';
import Tournament from './tournament/Tournament';
import Results from './results/Results';

export const routes = [
    {
        element: <Login />,
        path: "/"
    },
    {
        element: <Tournament />,
        path: "/play"
    },
    {
        element: <Results />,
        path: "/results"
    }
]