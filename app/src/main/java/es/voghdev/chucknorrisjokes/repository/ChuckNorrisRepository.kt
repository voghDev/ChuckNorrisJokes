/*
 * Copyright (C) 2018 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.voghdev.chucknorrisjokes.repository

import es.voghdev.chucknorrisjokes.usecase.GetJokeCategories
import es.voghdev.chucknorrisjokes.usecase.GetRandomJoke
import es.voghdev.chucknorrisjokes.usecase.GetRandomJokeByCategory
import es.voghdev.chucknorrisjokes.usecase.GetRandomJokeByKeyword

class ChuckNorrisRepository(
    val getRandomJokeDataSource: GetRandomJoke,
    val getJokeCategoriesDataSource: GetJokeCategories,
    val getRandomJokeByKeywordDataSource: GetRandomJokeByKeyword,
    val getRandomJokeByCategoryDataSource: GetRandomJokeByCategory
) : GetRandomJoke by getRandomJokeDataSource,
    GetRandomJokeByCategory by getRandomJokeByCategoryDataSource,
    GetRandomJokeByKeyword by getRandomJokeByKeywordDataSource,
    GetJokeCategories by getJokeCategoriesDataSource
