import { useEffect, useState } from 'react';
import './App.css';

const API_URL = process.env.REACT_APP_API_URL;

interface FoodItem {
  id: number,
  name: string
}

function App() {
  const [foodItems, setFoodItems] = useState<FoodItem[]>([]);
  const [newFoodItem, setNewFoodItem] = useState<string>('');

  useEffect(() => {
    fetchFoodItems();
  }, []);

  const fetchFoodItems = async () => {
    const response = await fetch(`${API_URL}/food-items`);
    const json = await response.json();
    setFoodItems(json);
  }

  const foodItemCreation = (event: React.SyntheticEvent) => {
    event.preventDefault()
    const foodItemToAdd = {
      id: foodItems.length + 1,
      name: newFoodItem,
    }
    console.log(foodItemToAdd);
    setFoodItems(foodItems.concat(foodItemToAdd));
    setNewFoodItem('')
  };

  return (
    <div className="App">
      <div>
        <form onSubmit={foodItemCreation}>
          <input type="text" value={newFoodItem} onChange={e => setNewFoodItem(e.target.value)} />
          <button type="submit">add</button>
        </form>
        <ul>
          {foodItems.map(item =>
            <li key={item.id}>{item.name}</li>
          )}
        </ul>
      </div>
    </div>
  );
}

export default App;
