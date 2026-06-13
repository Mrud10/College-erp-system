import { useState } from 'react'
import axios from 'axios'

function Login() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')

  const handleLogin = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/auth/login', {
        email,
        password
      })

      const { token, role } = response.data

      // save token and role to localStorage
      localStorage.setItem('token', token)
      localStorage.setItem('role', role)

      // redirect based on role
      if (role === 'ADMIN') window.location.href = '/admin'
      if (role === 'TEACHER') window.location.href = '/teacher'
      if (role === 'STUDENT') window.location.href = '/student'

    } catch (err) {
      setError('Invalid email or password')
    }
  }

  return (
    <div style={styles.container}>
      <div style={styles.box}>
        <h2 style={styles.title}>College ERP Login</h2>

        {error && <p style={styles.error}>{error}</p>}

        <input
          style={styles.input}
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <input
          style={styles.input}
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <button style={styles.button} onClick={handleLogin}>
          Login
        </button>
      </div>
    </div>
  )
}

const styles = {
  container: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',
    backgroundColor: '#f0f2f5'
  },
  box: {
    backgroundColor: 'white',
    padding: '40px',
    borderRadius: '8px',
    boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
    width: '360px',
    display: 'flex',
    flexDirection: 'column',
    gap: '16px'
  },
  title: {
    textAlign: 'center',
    color: '#333'
  },
  input: {
    padding: '12px',
    borderRadius: '4px',
    border: '1px solid #ddd',
    fontSize: '14px'
  },
  button: {
    padding: '12px',
    backgroundColor: '#4f46e5',
    color: 'white',
    border: 'none',
    borderRadius: '4px',
    fontSize: '16px',
    cursor: 'pointer'
  },
  error: {
    color: 'red',
    textAlign: 'center',
    fontSize: '14px'
  }
}

export default Login